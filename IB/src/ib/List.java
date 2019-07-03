/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ib;

/**
 *
 * @author austinschaaf
 */
public class List<Item> {
    private Node first;
    private int n;
    
    public List(){
        first = new Node();
    }
    
    private class Node<Item>{
        Item item;
        Node next;
    }
    
    public void add(Item item){
        Node oldFirst = first;
        
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        
        oldFirst.next = null;
               
        n = 1;
    }
    
    public Item getFirst(){
        return (Item) first.item;
    }
    
    public Item getPrevious(){
        return (Item) first.next.item;
    }
    
    public int size() {return n;}
    
    public void print(){
        Node x;
        for (x = first; x.next != null; x = x.next){
            System.out.println(x.item);
        }
    }
    private void remove(){
        Node x = first;
        while (x.next != null){
            x = x.next;
        }
        System.out.println("Item = " + x.item);
//        n -= 1;
    }
    
}
