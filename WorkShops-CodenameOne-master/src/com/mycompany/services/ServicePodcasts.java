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
import com.mycompany.entities.Podcasts;
import com.mycompany.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tahaj
 */
public class ServicePodcasts {
    public static ServicePodcasts instance = null;

    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServicePodcasts getInstance()
    {
        if(instance == null)
            instance = new ServicePodcasts();
        return instance;  

    }

    public ServicePodcasts()
    {
        req = new ConnectionRequest();
    }

    public void AjouterPodcasts(Podcasts podcasts)
    {
        String url = Statics.BASE_URL+"/AjouterPodcastsMobile?Title="+podcasts.getTitle()+"&Description="+podcasts.getDescription()+"&Rating="+podcasts.getRating()+"&Views="+podcasts.getViews()+"&File="+podcasts.getFile()+"&Image="+podcasts.getImage()+"&idCategorie="+podcasts.getIdcategorie();
        req.setUrl(url);
        req.addResponseListener ((e) -> {
             
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
  
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public ArrayList<Podcasts> AfficherCommandes()
    {

        ArrayList<Podcasts> result = new ArrayList<>();
        String url = Statics.BASE_URL+"/AfficherPodcastsMobile";
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
                        Podcasts p = new Podcasts();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String title = obj.get("title").toString();
                        String description = obj.get("description").toString();
                        float rating = Float.parseFloat(obj.get("rating").toString());
                        float views = Float.parseFloat(obj.get("views").toString());    
                        String file = obj.get("file").toString();    
                        String image = obj.get("image").toString();     
                        //float idcategorie = Float.parseFloat(obj.get("idcategorie").toString());                          
                        p.setId((int)id);
                        p.setTitle(title);
                        p.setDescription(description);
                        p.setRating((int)rating);
                        p.setViews((int)views);
                        p.setFile(file);
                        p.setImage(image);
                        //p.setIdcategorie((int)idcategorie);
                        //c.setProduit(produit.getId());
                        //c.setQuantite((int)quantite);
                        //c.setNamecateg(Namecateg);
                        //c.setDescriptioncateg(Descriptioncateg);
                        result.add(p);
                        
                        
                        System.out.println(p.getId()+" "+p.getTitle()+" "+p.getDescription()+" "+p.getRating()+" "+p.getViews()+" "+p.getFile()+" "+p.getImage()+" "+p.getIdcategorie());
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
    
    
        public boolean SupprimerPodcasts(int id)
    {
        String url = Statics.BASE_URL+"/SupprimerPodcastsMobile?id="+id;

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
        
    public boolean ModifierPodcasts(Podcasts podcasts)
    {
        String url = Statics.BASE_URL+"/ModifierPodcastsMobile?id="+podcasts.getId()+"&Title="+podcasts.getTitle()+"&Description="+podcasts.getDescription()+"&Rating="+podcasts.getRating()+"&Views="+podcasts.getViews()+"&File="+podcasts.getFile()+"&Image="+podcasts.getImage()+"&idCategorie="+podcasts.getIdcategorie();;
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
