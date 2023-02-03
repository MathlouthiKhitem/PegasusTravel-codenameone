/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Categorie;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tahaj
 */
public class ServiceCategorie {
    public static ServiceCategorie instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceCategorie getInstance()
    {
        if(instance == null)
            instance = new ServiceCategorie();
        return instance;  

    }

    public ServiceCategorie()
    {
        req = new ConnectionRequest();
    }

    public void AjouterCategorie(Categorie categorie)
    {
        String url = Statics.BASE_URL+"/AjouterCategorieMobile?namecateg="+categorie.getNamecateg()+"&Descriptioncateg="+categorie.getDescriptioncateg();
        req.setUrl(url);
        req.addResponseListener ((e) -> {
             
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
  
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public ArrayList<Categorie> AfficherCommandes()
    {

        ArrayList<Categorie> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AfficherCategorieMobile";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try 
                {
                    Map<String,Object>mapCategorie = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> ListOfMaps = (List<Map<String,Object>>) mapCategorie.get("root");
                    for(Map<String, Object> obj : ListOfMaps)
                    {
                        Categorie c = new Categorie();
                        float id = Float.parseFloat(obj.get("idcategorie").toString());
                        String Namecateg = obj.get("namecateg").toString();
                        String Descriptioncateg = obj.get("descriptioncateg").toString();

                        c.setIdcategorie((int)id);
                        //c.setProduit(produit.getId());
                        //c.setQuantite((int)quantite);
                        c.setNamecateg(Namecateg);
                        c.setDescriptioncateg(Descriptioncateg);
                        
                        result.add(c);
                        System.out.println(c.getIdcategorie()+" "+c.getNamecateg());
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    
    }    
    
    
        public boolean SupprimerCategorie(int id)
    {
        String url = Statics.BASE_URL+"/SupprimerCategorieMobile?id="+id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) 
            {
                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOk;
        
    }
        
    public boolean ModifierCategorie(Categorie categorie)
    {
        String url = Statics.BASE_URL+"/ModifierCategorieMobile?id="+categorie.getIdcategorie()+"&namecateg="+categorie.getNamecateg()+"&Descriptioncateg="+categorie.getDescriptioncateg();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) 
            {
                resultOk = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOk;

    }
    
}
