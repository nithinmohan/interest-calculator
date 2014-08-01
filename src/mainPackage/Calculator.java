package mainPackage;

import java.util.Calendar;
import java.util.Date;
import org.joda.time.*;
public class Calculator {
	private double amount;
	private double rateOfInterest;
	private int timeInDays;
	private int timeInMonths;
        private int timeInYears;
	private Date startDate;
	private int typeOfCalculation;
	private int typeOfMultiplication;
	private double finalAmount;
	private double interest;
	private Date dateOfExpire;
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
	public int getTimeInDays() {
		return timeInDays;
	}
	public void setTimeInDays(int time) {
		this.timeInDays = time;
	}

        public void setTimeInMonths(int timeInMonths) {
            this.timeInMonths = timeInMonths;
        }

        public void setTimeInYears(int timeInYears) {
            this.timeInYears = timeInYears;
        }

        public void setFinalAmount(double finalAmount) {
            this.finalAmount = finalAmount;
        }

        public void setInterest(double interest) {
            this.interest = interest;
        }
        
        public int getTimeInMonths() {
            return timeInMonths;
        }

        public int getTimeInYears() {
            return timeInYears;
        }
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public int getTypeOfCalculation() {
		return typeOfCalculation;
	}
	public void setTypeOfCalculation(int typeOfCalculation) {
		this.typeOfCalculation = typeOfCalculation;
	}
	public int getTypeOfMultiplication() {
		return typeOfMultiplication;
	}
	public void setTypeOfMultiplication(int typeOfMultiplication) {
		this.typeOfMultiplication = typeOfMultiplication;
	}
	public double getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(float finalAmount) {
		this.finalAmount = finalAmount;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(float interest) {
		this.interest = interest;
	}
	public Date getDateOfExpire() {
		return dateOfExpire;
	}
	public void setDateOfExpire(Date dateOfExpire) {
		this.dateOfExpire = dateOfExpire;
	}
	public void calculate() {
                setExpireDate();
		if(typeOfCalculation==0){
			calculateSimpleInterest();
		}
		else if(typeOfCalculation==1){
			calculateCompoundInterest();
		}
	}
	private void calculateSimpleInterest(){
                DateTime JStartDate=new DateTime(startDate);
                DateTime JExpireDate= new DateTime(dateOfExpire);
                int numOfDays=Days.daysBetween(JStartDate, JExpireDate).getDays();
                interest=((double)numOfDays/365)*(rateOfInterest/100)*amount;
		finalAmount=amount+interest;
	}
	private void calculateCompoundInterest(){
                DateTime JStartDate=new DateTime(startDate);
                DateTime JExpireDate= new DateTime(dateOfExpire);
                PeriodType p=PeriodType.yearMonthDay();
                Period difference = new Period(JStartDate, JExpireDate, p);
                int numOfMonths=difference.getMonths()+difference.getYears()*12;
                int completemonths=(int)(numOfMonths/typeOfMultiplication)*typeOfMultiplication;
                DateTime newDateTime=JStartDate.plus(Period.months(completemonths));
                int incompletedays=Days.daysBetween(newDateTime,JExpireDate).getDays();
                System.out.println(numOfMonths+" "+incompletedays);
                /*finalAmount=amount;
		while(timeInMonths>0){
			interest=((float)typeOfMultiplication/12)*(rateOfInterest/100)*finalAmount;
			finalAmount=finalAmount+interest;
			timeInMonths-=typeOfMultiplication;
		}*/
                int numOfTimesCompoundedInYear=12/typeOfMultiplication;
                int i=completemonths;
               finalAmount=amount;
                while(i>0){
                    finalAmount=Math.round(finalAmount+finalAmount*rateOfInterest/100*typeOfMultiplication/12);
                    i-=typeOfMultiplication;
                    System.out.println(finalAmount);
                }
              //finalAmount=amount*Math.pow(1+rateOfInterest/100/numOfTimesCompoundedInYear,(double)numOfTimesCompoundedInYear*completemonths/12);
                finalAmount=finalAmount+finalAmount*rateOfInterest/100*incompletedays/365;
                System.out.println(finalAmount);
		interest=finalAmount-amount;
	}
        private void setExpireDate(){
                 try{
                        DateTime JStartDate=new DateTime(startDate);
                        System.out.println(timeInMonths);
                        Period p=new Period(timeInYears,timeInMonths,0,timeInDays,0,0,0,0);
                        DateTime JExpireDate=JStartDate.plus(p);
                        dateOfExpire=JExpireDate.toDate();
                }
                catch(Exception ex){
                    dateOfExpire=null;
                }
        }
}
