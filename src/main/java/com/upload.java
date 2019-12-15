package com;

import com.vaadin.ui.Upload;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public class upload extends UploadDesigner{

    public upload(){

        uploadVOne.addClickListener(e -> {
            chooseDirectory();
        });

        uploadVTwo.addClickListener(e -> {
            chooseDirectory();
        });

        uploadTestCases.addClickListener(e -> {
            chooseDirectory();
            try {
                App.Start();
                JavaDiffReport.setRelatedTestCase();
            } catch (SolrServerException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
    }

    public void chooseDirectory(){
        DemoJFileChooser demo = new DemoJFileChooser();
        demo.test();
    }
}
