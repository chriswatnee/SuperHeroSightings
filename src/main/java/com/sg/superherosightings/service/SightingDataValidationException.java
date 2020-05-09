/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.service;

/**
 *
 * @author chris
 */
public class SightingDataValidationException extends Exception {
    
    public SightingDataValidationException(String message) {
        super(message);
    }
    
    public SightingDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
    
    @Override
    public String toString() {
        return "Sighting data validation";
    }
}
