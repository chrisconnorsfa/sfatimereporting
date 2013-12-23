/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scottishfriendly.timereporting.utils;

import com.scottishfriendly.timereporting.utils.IStandardFields.DateType;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chriconn
 */
public interface IStandardFields {
    
    public enum Project{
        
        SFMARK(172903),SFAIT(172904),SFATOGR(172906),SFFIN(172905),HELL(172907),SFACT(172908),SFRISK(172909),ITSFA(172910),SFCUST(172911);
        
        Integer id;

        private Project(Integer id) {
            this.id = id;
        }
        
        @Override
        public String toString(){
            return id.toString();
        }
        
        public Integer getId(){
            return id;
        }
    }
    
    public enum DateType{

        ALL("-- All --"), CREATED("Created"), RESOLVED("Resolved"), DUE("Due"), UPDATED("Updated");
        
        private String name;

        DateType(final String name){
            this.name=name;
        }

        public String getName(){
            return this.name;
        }

        public static List<String> getNames(DateType[] dateType){
            List<String> retVal = new ArrayList<String>();
            for(DateType d: dateType){
                retVal.add(d.getName());
            }
            return retVal;
        }
        
         public static Map<String,String> getNamesMap(DateType[] dateType){
            Map<String,String> retVal = new LinkedHashMap<String,String>();
            for(DateType d: dateType){
                retVal.put(d.toString(),d.getName());
            }
            return retVal;
        }
        
    }
    
    public enum Status{
        
        ALL("-- All --",0), OPEN("Open",1), IN_PROGRESS("In Progress",2), REOPENED("Re-Opened",4),RESOLVED("Resolved",5), CLOSED("Closed",6),
        AWAITING_APPROVAL("Awaiting Approval",10001), ESTIMATE_IN_PROGRESS("Estimate In Progress",10004), WAITING_COST_APPROVAL("Waiting Cost Approval",10005),DEVELOPMENT("Development",10006),UAT("UAT",10007)
        ;
        
        private String name;
        private Integer id;


        Status(final String name, final Integer id){
            this.name=name;
            this.id=id;
        }



        public String getName(){
            return this.name;
        }

        public Integer getId() {
            return id;
        }

      
        public static List<String> getNames(Status[] status){
            List<String> retVal = new ArrayList<String>();
            for(Status s: status){
                retVal.add(s.getName());
            }
            return retVal;
        }
        
        public static Map<Integer,String> getNamesMap(Status[] status){
            Map<Integer,String> retVal = new LinkedHashMap<Integer,String>();
            for(Status s: status){
                retVal.put(s.getId(),s.getName());
            }
            return retVal;
        }

    }
    
}
