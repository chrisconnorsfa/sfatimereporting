/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bssuk.test.controllers;

/**
 *
 * @author chriconn
 */
public class NewClass {

    public NewClass() {
    }
    
    public static void main(String[] args) {
        String s = null;
        
        try {
            if (s != null && s.isEmpty()) {
                System.out.println("1 bad");
            } else {
                System.out.println("1 ok");
            }
        } catch (Exception e) {
            System.out.println("1 bad");
        }
        
        try {
            if (s.isEmpty() && s != null) {
                System.out.println("2 bad");
            }
        } catch (Exception e) {
            System.out.println("2 ok");
        }
    }
}
