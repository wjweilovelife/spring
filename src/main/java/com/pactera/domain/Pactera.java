package com.pactera.domain;

import java.io.Serializable;

/**
 * Created by pactera on 2017/4/18.
 */
public class Pactera implements Serializable {


   private static final long serialVersionUID = -1842054578703230814L;

   private String name;
   private String address;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   @Override
   public String toString() {
      return "Pactera{" +
            "name='" + name + '\'' +
            ", address='" + address + '\'' +
            '}';
   }
}
