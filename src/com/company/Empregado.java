
package com.company;

public class Empregado {
    public String name;
    public int number; //código do empregado
    public int[] workedDays; 
    /*falta criar a classe Date 
    public Date date;
    */
    public double vencimento;
    
    public Empregado(){
        workedDays = new int[12];
        
        if(name == null){
            this.name = "";
        } else {
            this.name = name;
        }
        number = 0;
        
        //this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int[] getWorkedDays() {
        return workedDays;
    }

    public void setWorkedDays(int[] workedDays) {
        this.workedDays = workedDays;
    }
    
    //double salario = vencimento + (4.79*workedDays) + (0.5*anoTrabalhado);
    
    public class Gestor extends Empregado{
        //double salario = (vencimento*0.15) + (4.79*workedDays) + (0.5*anoTrabalhado);
        
    }
    
    public class Motorista extends Empregado{
        //double salario = vencimento + (4.79*workedDays) + (0.5*anoTrabalhado) + (km*valorKM);
        
    }
    
    public class Comercial extends Empregado{
        //double salario = vencimento + (4.79*workedDays) + (0.5*anoTrabalhado) + percentagemVendas;
   
    }
    
}
