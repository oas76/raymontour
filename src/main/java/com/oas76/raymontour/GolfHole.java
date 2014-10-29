package com.oas76.raymontour;

import com.googlecode.objectify.annotation.*;

/**
 * Created by oddaskaf on 28.10.14.
 */

public class GolfHole {
        private int number;
        private String name;
        private int par;
        private int length;
        private int index;

        public GolfHole(){
            // No Args Constructor
        }

        public GolfHole(int number,String name, int par, int length, int index)
        {
            this.number = number;
            this.name = name;
            this.par = par;
            this.length = length;
            this.index = index;
        }

        public int getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        public int getPar() {
            return par;
        }

        public int getLength() {
            return length;
        }

        public int getIndex() {
            return index;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPar(int par) {
            this.par = par;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public void setIndex(int index) {
            this.index = index;
        }






}
