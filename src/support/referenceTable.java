/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.util.LinkedHashMap;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author CALVINO
 */
public final class referenceTable {
    
    String  line;
    
    public   LinkedHashMap<String,String> cuoCod = new LinkedHashMap<>();
    public   LinkedHashMap<String,String> ctn_ind = new LinkedHashMap<>();
    public   LinkedHashMap<String,String> ctn_type = new LinkedHashMap<>();
    public   LinkedHashMap<String,String> country = new LinkedHashMap<>();
    public   LinkedHashMap<String,String> locode = new LinkedHashMap<>();
    public   LinkedHashMap<String,String> mode_trans = new LinkedHashMap<>();
    public   LinkedHashMap<String,String> nature = new LinkedHashMap<>();
    public   LinkedHashMap<String,String> pkg_table = new LinkedHashMap<>();
    public   LinkedHashMap<String,String> scan_docs = new LinkedHashMap<>();
    public   LinkedHashMap<String,String> sealing_party = new LinkedHashMap<>();
    public   LinkedHashMap<String,String> localisation = new LinkedHashMap<>();
    
    public referenceTable(){
        this.cuoCod = setSet(cuoCod, "cuoCod", 5);
        this.ctn_ind = setSet(ctn_ind, "ctn_ind", 3);
        this.ctn_type = setSet(ctn_type, "ctn_type", 2);
        this.country = setSet(country, "country", 2);
        this.locode = setSet(locode, "locode", 5); 
        this.mode_trans = setSet(mode_trans, "mode_trans", 2);
        this.nature = setSet(nature, "nature", 2);
        this.pkg_table = setSet(pkg_table, "pkg_table", 2);
        this.scan_docs = setSet(scan_docs, "scan_docs", 3);
        this.sealing_party = setSet(sealing_party, "sealing_party", 3);
        this.localisation = setSet(localisation, "localisation", 0);
    }

    public LinkedHashMap<String,String> setSet(LinkedHashMap<String,String> lm, String lmRef, int nCh) {
        try
        {
            if(nCh == 0){
                 BufferedReader br = new BufferedReader(new FileReader("./files/" + lmRef));
                //BufferedReader br3 = new BufferedReader(fr3);
                while ((line = br.readLine()) != null)
                {
                    String code = line.substring(0, line.indexOf("/")) + " " + line.substring(line.indexOf("/") + 1);
                    String name = line.substring(line.indexOf("/") + 1);
                    lm.put(code, name);
                    //System.out.println(lm.get(code));
                }
                Thread.sleep(100);
            }else{
                //File f = new File("/files/" + lmRef);
    //            FileReader fr = new FileReader("/files/" + lmRef);
    //            
    //            System.out.println(fr.getClass().getFields());
                BufferedReader br = new BufferedReader(new FileReader("./files/" + lmRef));
                //BufferedReader br3 = new BufferedReader(fr3);
                while ((line = br.readLine()) != null)
                {
                    String code = line;
                    String name = line.substring(nCh+1);
                    lm.put(code, name);
                    //System.out.println(lm.get(code));
                }
                Thread.sleep(100);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return lm;
    }

}