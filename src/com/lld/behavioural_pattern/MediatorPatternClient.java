package com.lld.behavioural_pattern;

import java.util.ArrayList;
import java.util.List;

//Mediator defines an object that encapsulates how a 
//set of other objects interact with one another

// It restricts direct communication between
// objects and forces them to collaborate via a mediator, hence reducing the dependencies between them.


//useCase : Online Auction System

interface IBidder{
	void placeBid(int bidAmount);
	void recieveBidNotification(int bidAmount, String currentBidder);
	String getName();
}
 
class Bidder implements IBidder{
	String name;
	AuctionMediator auctionMediator;
	
	Bidder(String name, AuctionMediator auctionMediator){
		this.name = name;
		this.auctionMediator = auctionMediator;
		auctionMediator.addBidder(this);
	}
	
	@Override
	public void placeBid(int bidAmount) {
		auctionMediator.placeBidder(this,bidAmount);
	}
	@Override
	public void recieveBidNotification(int bidAmount, String currentBidder) {
		System.out.println("Bidder: "+name+" got the notification that "+currentBidder+" has put bid of : "+bidAmount);
	}
	@Override
	public String getName() {
		return name;
	}
}
interface IAuctionMediator{
	
	void addBidder(Bidder bidder);
	void placeBidder(Bidder bidder, int bidAmount);
	
}
class AuctionMediator implements IAuctionMediator{
	
	List<Bidder> bidders = new ArrayList<Bidder>();

	public void addBidder(Bidder bidder) {
		
		bidders.add(bidder);
	}

	@Override
	public void placeBidder(Bidder currentBidder, int bidAmount) {
		
		for(Bidder bidder : bidders) {
			if(!bidder.getName().equals(currentBidder.getName())) {
				bidder.recieveBidNotification(bidAmount,currentBidder.getName());
			}
		}
		
	}
	
}
public class MediatorPatternClient{
	public static void main(String...strings) {
		
		//in this case all the bidder object will communicate or know the state via the mediatorAuction
		AuctionMediator auctionMediator = new AuctionMediator();
		Bidder bidder1 = new Bidder("padmesh",auctionMediator);
		Bidder bidder2 = new Bidder("amar",auctionMediator);
		Bidder bidder3 = new Bidder("logesh",auctionMediator);
		Bidder bidder4 = new Bidder("karthi",auctionMediator);
		bidder1.placeBid(1000);
		bidder2.placeBid(2500);
		bidder1.placeBid(3000);
		bidder3.placeBid(3100);
		bidder1.placeBid(4000);
		bidder4.placeBid(10000);
	}
}