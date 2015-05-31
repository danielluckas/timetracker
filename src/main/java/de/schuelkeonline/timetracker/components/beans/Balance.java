/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.schuelkeonline.timetracker.components.beans;

/**
 *
 * @author danielschuelke
 */
public class Balance {

    private Float weekBalance;
    private Float monthBalance;
    private Float totalBalance;

    public Balance(Float weekBalance, Float monthBalance, Float totalBalance) {
        this.weekBalance = weekBalance;
        this.monthBalance = monthBalance;
        this.totalBalance = totalBalance;
    }

    public Balance() {
        this.weekBalance = 0.00f;
        this.monthBalance = 0.00f;
        this.totalBalance = 0.00f;
    }

    public Float getWeekBalance() {
        return weekBalance;
    }

    public void setWeekBalance(Float weekBalance) {
        this.weekBalance = weekBalance;
    }

    public Float getMonthBalance() {
        return monthBalance;
    }

    public void setMonthBalance(Float monthBalance) {
        this.monthBalance = monthBalance;
    }

    public Float getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Float totalBalance) {
        this.totalBalance = totalBalance;
    }

}
