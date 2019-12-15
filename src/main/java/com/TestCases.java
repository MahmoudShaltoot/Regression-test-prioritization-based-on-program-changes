package com;

import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestCases extends TestCasesDesigner {
    Grid<MethodChanged> methodChangedGrid ;
    public TestCases(){

        methodChangedGrid = new Grid<>(MethodChanged.class);

        if(JavaDiffReport.methodChangedList.size() != 0){

            methodChangedGrid.setItems(JavaDiffReport.methodChangedList);
            methodChangedGrid.setColumns("mClassName","mMethodName","numOfAddition","numOfDeletion","mRelatedTestCase");

            methodChangedGrid.setSizeUndefined();
            methodChangedGrid.setWidth(950,Unit.PIXELS);


            testCasesLayout.addComponent(methodChangedGrid);
        }

        int[] tmpList = new int[JavaDiffReport.methodChangedList.size()];

        for(int counter = 0; counter <JavaDiffReport.methodChangedList.size(); counter++){
            tmpList[counter] = JavaDiffReport.methodChangedList.get(counter).numOfDeletion +
                    JavaDiffReport.methodChangedList.get(counter).numOfAddition;
        }

        removeDuplicates(tmpList);

        Arrays.sort(tmpList);

        Label testCases = new Label("Test Cases Prioritization : ");
        testCases.addStyleName("testCasePrioritizationLabel");

        testCasesLayout.addComponent(testCases);
        int labelNum = 1;

        List<String> testCasesList = new ArrayList<String>();

        for(int counter = JavaDiffReport.methodChangedList.size() -1; counter >= 0;counter-- ){
            for(MethodChanged methodChanged : JavaDiffReport.methodChangedList){
                if (methodChanged.numOfAddition + methodChanged.numOfDeletion == tmpList[counter] && tmpList[counter] != 0){
                    if(!testCasesList.contains(methodChanged.mRelatedTestCase))
                        testCasesList.add(methodChanged.mRelatedTestCase);
                }
            }
        }

        for(String tmpString : testCasesList) {
            tmpString = tmpString.substring(1,tmpString.length()-1);

            Label l = new Label(labelNum + " _ " + tmpString);
            l.addStyleName("testCaseName");
            testCasesLayout.addComponent(l);
            labelNum++;
        }
    }

    public static int[] removeDuplicates(int []s){
        int result[] = new int[s.length], j=0;
        for (int i : s) {
            if(!isExists(result, i))
                result[j++] = i;
        }
        return result;
    }
    private static boolean isExists(int[] array, int value){
        for (int i : array) {
            if(i==value)
                return true;
        }
        return false;
    }
}
