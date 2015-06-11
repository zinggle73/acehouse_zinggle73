package com.service.common.util;

import java.util.Comparator;
import java.util.HashMap;

public class MapComparator implements Comparator<HashMap<String, Object>>
{
	private final String key;

	public MapComparator( String key )
	{
		this.key = key;
	}

	@Override
	public int compare( HashMap<String, Object> first, HashMap<String, Object> second )
	{
		int result = ( (Integer)first.get( key ) ).compareTo( (Integer)second.get( key ) );
		return result;
	}
}
