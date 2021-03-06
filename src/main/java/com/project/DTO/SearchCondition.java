package com.project.DTO;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {
    private Integer page = 1;
    private Integer pageSize = 10;
    //private Integer offset = 0;
    private String keyword = "";
    private String keyword2 = "";
    private String option = "";
    private String sort = "";
    private String sortType="";

    public SearchCondition(){}
    public SearchCondition(Integer page, Integer pageSize, String keyword, String keyword2, String option, String sort, String sortType) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.keyword2 = keyword2;
        this.option = option;
        this.sort = sort;
        this.sortType = sortType;
    }

    public String getQueryString(Integer page){
        // ?page=1&pageSize=10&option=T&keyword="title"
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize",pageSize)
                .queryParam("option",option)
                .queryParam("keyword",keyword)
                .queryParam("keyword2",keyword2)
                .queryParam("sort",sort)
                .queryParam("sortType",sortType)
                .build().toString();
    }

    public String getQueryString(){
        return getQueryString(page);
    }
    
    public String getQueryString(String keyword, String keyword2){
        // ?page=1&pageSize=10&option=T&keyword="title"
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize",pageSize)
                .queryParam("option",option)
                .queryParam("keyword",keyword)
                .queryParam("keyword2",keyword2)
                .queryParam("sort",sort)
                .queryParam("sortType",sortType)
                .build().toString();
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getOffset() {
        return (page-1)*pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
    
	public String getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	
	@Override
	public String toString() {
		return "SearchCondition [page=" + page + ", pageSize=" + pageSize + ", keyword=" + keyword + ", keyword2="
				+ keyword2 + ", option=" + option + ", sort=" + sort + ", sortType=" + sortType + "]";
	}
	
   
}