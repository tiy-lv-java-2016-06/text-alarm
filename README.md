# Text Alarm

## Description
We are going to use Strip and Twilio to accomplish something simple.  You are going to write a system that will charge a user money to text them in the mroning.  This will help you understand Stripe, Twilio and scheduling with spring.

## Requirements
* Create an app that will allow users to signup with a phone number (this might mean a seperate page to register the user)
* Then offer them to pay you $20 (with the simple stripe form from class)
* Once they pay make sure your controller captures the token from Stripe
* Save the charge id from stripe in the User object
* Every morning at 7am (You can test this in various ways) send a text via Twilio at some arbitrary time of your choosing daily.

## Hard Mode
* Create the ability to shut off the notifications and refund the money back to the user.

## Resources
* [Github Repo](https://github.com/tiy-lv-java-2016-06/text-alarm)
* [Stripe Documentation](https://stripe.com/docs)
* [Twilio for Java](https://github.com/twilio/twilio-java)
* [Spring Scheduling](https://spring.io/guides/gs/scheduling-tasks/) 
