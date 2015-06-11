package com.ace.web.vo;

import java.util.ArrayList;
import java.util.List;

// 코드내역
public class Codez 
{
	private long id;
	private String groupCode = "00000";				// 상위코드	
	private String detailCode;						// 하위코드
	private String codeName;						// 코드명
	private String codeExplan;						// 코드설명
	private String codeOr = "Y";					// 코드사용여부
	
	private List<Codez> codezs;					// 코드목록

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getDetailCode() {
		return detailCode;
	}

	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeExplan() {
		return codeExplan;
	}

	public void setCodeExplan(String codeExplan) {
		this.codeExplan = codeExplan;
	}

	public String getCodeOr() {
		return codeOr;
	}

	public void setCodeOr(String codeOr) {
		this.codeOr = codeOr;
	}

	public List<Codez> getCodezs()
	{
		return codezs;
	}

	public void setCodezs( List<Codez> codezs )
	{
		this.codezs = codezs;
	}
	
	public void addCodes(Codez codez)
	{
        if(codezs == null)
        {
        	codezs = new ArrayList<Codez>();
        }

        getCodezs().add(codez);
    }
	
}
