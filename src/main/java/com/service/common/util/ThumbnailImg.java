/**
 * Copyright 2008 sys4u.co.kr
 * 
 * Licensed under the Intromobile. Co, Ltd License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * 
 * Created on 2008. 10. 06
 * 
 * Project: Lotte_DFS_Backoffice_Project Package: com.lottedfs.backoffice.util
 * Filename : ThumbnailImg.java
 * 
 * Site : http://www.sys4u.co.kr Author : isaacjc
 */
package com.service.common.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import com.service.common.logger.ComLogger;
import com.service.common.logger.ComLoggerFactory;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ThumbnailImg
{
	protected static final ComLogger logger = ComLoggerFactory.getLogger( ThumbnailImg.class );

	private String path;
	private String sourcefile;
	private List<ThumbnailImg.Size> sizes;
	private String ext;

	/**
	 * 생성자 디렉토리 경로, 원본이미지 파일
	 * 
	 * @param path
	 * @param sourcefile
	 */
	public ThumbnailImg()
	{
	}

	/**
	 * 썸네일 이미지 생성
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean create( String path, String sourcefile, List<ThumbnailImg.Size> sizes ) throws Exception
	{
		logger.debug( "=========ThumbnailImg.create" );
		long starttime = System.currentTimeMillis();
		setPath( path );
		setSourcefile( sourcefile );
		setSizes( sizes );
		check();

		logger.debug( "path : " + getPath() );
		logger.debug( "sourcefile : " + getSourcefile() );

		boolean blnCreateImag = true;
		try
		{
			File inFile = new File( new File( getPath() ), getSourcefile() );
			BufferedImage bufImg = ImageIO.read( inFile );
			for ( Iterator<ThumbnailImg.Size> iter = sizes.iterator(); iter.hasNext(); )
			{
				if ( !createImg( bufImg, iter.next() ) )
				{
					blnCreateImag = false;
					break;
				}
			}
		}
		catch ( Exception e )
		{
			blnCreateImag = false;
		}
		finally
		{
			long endtime = System.currentTimeMillis();
			logger.debug( "elapse time : " + ( endtime - starttime ) );
		}

		return blnCreateImag;
	}

	/**
	 * 실제 썸네일 이미지 생성
	 * 
	 * @param bufImg
	 * @param size
	 * @return
	 */
	private boolean createImg( BufferedImage bufImg, ThumbnailImg.Size size )
	{

		BufferedOutputStream outStrm = null;
		try
		{
			int nNewWidth = size.getWidth();
			int nNewHeight = size.getHeight();

			BufferedImage newImg = new BufferedImage( nNewWidth, nNewHeight, BufferedImage.TYPE_INT_RGB );
			Graphics2D grph = newImg.createGraphics();

			grph.drawImage( bufImg, 0, 0, nNewWidth, nNewHeight, null );

			outStrm = new BufferedOutputStream( new FileOutputStream( new File( new File( getPath() ),
					size.getFilename() ) ) );

			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder( outStrm );

			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam( newImg );
			param.setQuality( 1F, false );

			encoder.setJPEGEncodeParam( param );
			encoder.encode( newImg );
		}
		catch ( Exception e )
		{
			return false;
		}
		finally
		{
			if ( outStrm != null )
				try
				{
					outStrm.close();
				}
				catch ( Exception e )
				{}
		}
		return true;
	}

	/**
	 * 필수입력 체크
	 * 
	 * @throws Exception
	 */
	private void check() throws Exception
	{
		assertNotEmpty( path, "필수 입력 항목이 없습니다. path" );
		assertNotEmpty( sourcefile, "필수 입력 항목이 없습니다. sourcefile" );

		if ( sizes == null || sizes.size() == 0 )
		{
			throw new Exception( "필수 입력 항목이 없습니다. sizes" );
		}
	}

	private void assertNotEmpty( String str, String msg ) throws Exception
	{
		if ( str == null || "".equals( str ) )
		{
			throw new Exception( msg );
		}
	}

	public String getPath()
	{
		return path;
	}

	public void setPath( String path )
	{
		this.path = path;
	}

	public String getSourcefile()
	{
		return sourcefile;
	}

	public void setSourcefile( String sourcefile )
	{
		this.sourcefile = sourcefile;
	}

	public List<ThumbnailImg.Size> getSizes()
	{
		return sizes;
	}

	public void setSizes( List<ThumbnailImg.Size> sizes )
	{
		this.sizes = sizes;
	}

	public String getExt()
	{
		return ext;
	}

	public void setExt( String ext )
	{
		this.ext = ext;
	}

	/**
	 * @author 정윤호 썸네일 이미지 정보 Class
	 */
	static class Size
	{
		private int width;
		private int height;
		private String filename;

		Size( int width, int height, String filename )
		{
			setWidth( width );
			setHeight( height );
			setFilename( filename );
		}

		public int getWidth()
		{
			return width;
		}

		public void setWidth( int width )
		{
			this.width = width;
		}

		public int getHeight()
		{
			return height;
		}

		public void setHeight( int height )
		{
			this.height = height;
		}

		public String getFilename()
		{
			return filename;
		}

		public void setFilename( String filename )
		{
			this.filename = filename;
		}

	}
}
