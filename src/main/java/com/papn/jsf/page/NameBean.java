/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papn.jsf.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author calviniloki
 */
@Named("nameBean")
@ViewScoped
public class NameBean implements Serializable{
    private List<String> listName;
    
    @PostConstruct
    public void init(){
       listName = new ArrayList<>();
        listName.add("Calvin ILOKI");
        listName.add("Warren ILOKI");
        listName.add("Tresor ILOKI");
        listName.add("Darwin ILOKI");
    }

    public List<String> getListName() {
        return listName;
    }

    public void setListName(ArrayList<String> listName) {
        this.listName = listName;
    }
    
}
