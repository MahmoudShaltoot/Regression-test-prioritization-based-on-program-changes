package com;

import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class CodeReport extends CodeReportDesigner {

    public CodeReport(){
        verticalLayout.addStyleName("CodeComparisionLayout");

        for(Jclass jclass : JavaDiffReport.classObj){
            Label classLabel = new Label(jclass.path);
            classLabel.addStyleName(ValoTheme.LABEL_BOLD);
            verticalLayout.addComponent(classLabel);
            verticalLayout.addComponent(new Label());
            verticalLayout.addComponent(new Label());

            for(Statment statment : jclass.Statments){
                if(statment.getType() == 'A'){
                        Label s = new Label(statment.getLineNumber() + "  :  "+ statment.getLine());
                        s.addStyleName("green");
                        verticalLayout.addComponent(s);
                }else if(statment.getType() == 'D'){
                        Label s = new Label(statment.getLineNumber() + "  :  "+ statment.getLine());
                        s.addStyleName("red");
                        verticalLayout.addComponent(s);
                }else{
                    Label normalStatement = new Label(statment.getLineNumber() + " : "+
                            statment.getLine());
                        verticalLayout.addComponent(normalStatement);
                    }
                }
            }
        }
    }

