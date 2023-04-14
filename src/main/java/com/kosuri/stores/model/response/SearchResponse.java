package com.kosuri.stores.model.response;

import com.kosuri.stores.model.search.SearchResult;

import java.util.List;

public class SearchResponse extends GenericResponse {
    private List<SearchResult> searchResultList;

    public List<SearchResult> getSearchResultList() {
        return searchResultList;
    }

    public void setSearchResultList(List<SearchResult> searchResultList) {
        this.searchResultList = searchResultList;
    }
}

