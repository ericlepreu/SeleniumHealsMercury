package com.mercury.travel.builders;

public class FlightDetails {
	private final String departFrom; //required
	private final String fromMonth; //required
	private final String fromDay; //required
	private final String arriveIn; //required
	private final String toMonth; //required
	private final String toDay; //required
	private final boolean isRoundTrip; //optional (otherwise just keep default RoundTrip)
	private final String noOfPassengers; //optional (otherwise just keep default 1)
	
	private FlightDetails(FlightDetailsBuilder builder) {
		this.departFrom = builder.departFrom;
		this.fromMonth = builder.fromMonth;
		this.fromDay = builder.fromDay;
		this.arriveIn = builder.arriveIn;
		this.toMonth = builder.toMonth;
		this.toDay = builder.toDay;
		this.isRoundTrip = builder.isRoundTrip;
		this.noOfPassengers = builder.noOfPassengers;
	}
	
	public String getDepartFrom() {
		return departFrom;
	}
	public String getFromMonth() {
		return fromMonth;
	}
	public String getFromDay() {
		return fromDay;
	}
	public String getArriveIn() {
		return arriveIn;
	}
	public String getToMonth() {
		return toMonth;
	}
	public String getToDay() {
		return toDay;
	}
	public boolean getIsRoundTrip() {
		return isRoundTrip;
	}
	public String getNoOfPassengers() {
		return noOfPassengers;
	}
	
	public static class FlightDetailsBuilder {
		private final String departFrom;
		private final String fromMonth;
		private final String fromDay;
		private final String arriveIn;
		private final String toMonth;
		private final String toDay;
		private boolean isRoundTrip = true;
		private String noOfPassengers = "1";
		
		public FlightDetailsBuilder(String departFrom, String fromMonth, String fromDay, String arriveIn, String toMonth, String toDay) {
			this.departFrom = departFrom;
			this.fromMonth = fromMonth;
			this.fromDay = fromDay;
			this.arriveIn = arriveIn;
			this.toMonth = toMonth;
			this.toDay = toDay;
		}
		
		public FlightDetailsBuilder isRoundTrip(boolean isRoundTrip) {
			this.isRoundTrip = isRoundTrip;
			return this;
		}
		
		public FlightDetailsBuilder noOfPassengers(String noOfPassengers) {
			this.noOfPassengers = noOfPassengers;
			return this;
		}
		
		public FlightDetails build() {
			return new FlightDetails(this);
		}
	}
}
