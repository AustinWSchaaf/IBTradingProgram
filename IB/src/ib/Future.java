/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ib;
import com.ib.client.Contract;

/**
 *
 * @author austinschaaf
 */
public class Future {
    
    public static Contract getES(){
        Contract contract = new Contract();
        contract.symbol("ES");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("GLOBEX");
        contract.lastTradeDateOrContractMonth("20180615");
        return contract;
    }
    
    public static Contract getYM(){
        Contract contract = new Contract();
        contract.symbol("YM");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("ECBOT");
        contract.lastTradeDateOrContractMonth("20180615");
        return contract;
    }
    
    public static Contract getZB(){
        Contract contract = new Contract();
        contract.symbol("ZB");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("ECBOT");
        contract.lastTradeDateOrContractMonth("20171219");
        return contract;
    }
    
    public static Contract getGC(){
        Contract contract = new Contract();
        contract.symbol("GC");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("NYMEX");
        contract.lastTradeDateOrContractMonth("20180226");
        return contract;
    }
    
    public static Contract getNQ(){
        Contract contract = new Contract();
        contract.symbol("NQ");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("GLOBEX");
        contract.lastTradeDateOrContractMonth("20180921");
        return contract;
    }
    
    public static Contract getCL(){
        Contract contract = new Contract();
        contract.symbol("CL");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("NYMEX");
        contract.lastTradeDateOrContractMonth("20180620");
        return contract;
    } 
}
