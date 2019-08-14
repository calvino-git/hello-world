/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.papn.jsf.page;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author calviniloki
 */
@Named("bonjour")
@RequestScoped
public class WelcomeBean implements Serializable {
    private String nom;
    private String message;
    
    @Inject
    NameBean nameBean;

    
    public String bonjour(){
        this.message = nom;
        System.out.println(nameBean.getListName().size());
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("message", nameBean.getListName().indexOf(message));
        return "message.xhtml?faces-redirect=true";
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
