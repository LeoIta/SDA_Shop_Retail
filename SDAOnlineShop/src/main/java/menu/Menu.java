package menu;

import model.*;
import repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public void displayProduct (){

    }

    public static void welcomeToShopMessage(){
        System.out.println("Welcome to LeoMi Shop");
    }

    public static Customer identifyUser(){
        System.out.println(" Select how you want to shop : 1) New User ( register and shop) 2) Already have an account (login and shop) 3) Shop as a guest");

        Scanner scan = new Scanner(System.in);
        int userType = scan.nextInt();

        while( userType!=1 && userType!=2 && userType!=3){
            System.out.println("wrong choice : please type (1) , (2) or (3) from your Keyboard");
            userType = scan.nextInt();
        }

        if (userType == 1){
            String firstName, lastName, email, telephone, country, city, postalCode, street, userName, password;

            System.out.println(" In oder to register please provide us with the following information : first Name  , Last Name, email , telephone and Address");
            System.out.println("1) enter your firstname : ");
            firstName = scan.nextLine();

            System.out.println("2) Enter your last name : ");
            lastName = scan.nextLine();

            System.out.println("3) Enter your email : ");
            email = scan.nextLine();

            System.out.println("4) Enter your telephone  : ");
            telephone = scan.nextLine();



            System.out.println(" Now lets add your delivery information: ");

            System.out.println("1) Enter your Country: ");
            country = scan.nextLine();

            System.out.println("2) Enter your City: ");
            city    = scan.nextLine();

            System.out.println("3) Enter your postal Code : ");
            postalCode = scan.nextLine();

            System.out.println("4) Enter your postal Code : ");
            street = scan.nextLine();

            System.out.println(" Create a username and a password for your account ");

            System.out.println("1) Enter your username: ");
            userName = scan.nextLine();

            System.out.println("2) Enter your password: ");
            password    = scan.nextLine();

            Login login = new Login(userName, password); // add a function to check unique username.
            LoginRepository.saveNewLogin(login);

            Address address = new Address(country,city,postalCode); // street to add
            AddressRepository.saveNewAddress(address);

            int accountId = LoginRepository.getLastLoginId();
            int addressId = AddressRepository.getLastAddressId();

            // we save the new cst in the database to generate his customer id.
            CustomerRepository.saveNewCustomer(new Customer(firstName,lastName,email,telephone,addressId,accountId));

            // here we want to get the new customer ( latest position ) from the database
            return CustomerRepository.getLastAddedCustomer();

        }else if(userType == 2){
            String userName , password;

            System.out.println(" Please Login  - \n Enter: your username: ");
            scan.nextLine();
            userName = scan.nextLine();
            System.out.println("Enter your password");
            //scan.nextLine();
            password = scan.nextLine();
            System.out.println(password.equals("Mikael")+"    |    "+ userName.equals("Mikael"));
            List<Login> logins = LoginRepository.findAccountId(userName, password);

            while ( logins.size() == 0){

                System.out.println(" there is no user with this username and password : please retype your username and password");
                System.out.println(" Enter: your username: ");
                scan.nextLine();
                userName = scan.nextLine();
                System.out.println("Enter your password");
                scan.nextLine();
                password = scan.nextLine();

            }

            int nbOfAccountId = LoginRepository.findAll().size();
            int accountId = LoginRepository.findAll().get(nbOfAccountId-1).getAccountID();

            int nbOfCustomer = CustomerRepository.findByAccountId(accountId).size();
            return  CustomerRepository.findByAccountId(accountId).get(nbOfCustomer-1) ;


        }else {

            return new Customer("guest");

        }
    }

    public static void welcomeCustomer(Customer customer, int usertype){
        if ( usertype == 3 ){
            System.out.println( " You are shopping as a guest. Enjoy your shopping ");
        }else{
            System.out.println(" Welcome Dear "+customer.getFirstName()+" "+customer.getLastName()+ " Enjoy your shopping ");
        }
    }

    public static void displayAvailableItem (List<Product> productList){
        System.out.println("Choose the Item you wish to buy \n Choice(*)    Item Type             | Description        | Price             |In Stock ");
        int totalProduct = productList.size();

        for (int i=0; i<totalProduct ; i++){
            String type = productList.get(i).getType();
            String color = productList.get(i).getColor();
            String size = productList.get(i).getSize();
            int price = productList.get(i).getPrice();
            int stock = StorageRepository.getQtyByCode( productList.get(i).getProductCode());

            System.out.println((i+1)+") Type:  "+type + " Color  " + color + " Size:  " + size + " Price  " + price + " Qty in Stock : " + stock);

        }
    }

    public static void displayDelivery(List<Delivery> deliveryList){
        int nbOfDeliveryCpy = deliveryList.size();
        System.out.println(" Press the correct number to choose you Delivery method: ");
        for(int i =0; i< nbOfDeliveryCpy; i++){
            System.out.println((i+1)+") Company : "+deliveryList.get(i).getName() + "  Delivery Cost: " + deliveryList.get(i).getDeliveryCost() );
        }
    }

    public static int displayBill(List<Product> productList){

        System.out.println("_________________________________________________________________________________________________________________");
        int nbOfItem = productList.size();
        int amount =0;

        for (int i =0; i< nbOfItem; i++){
            String type = productList.get(i).getType();
            String color = productList.get(i).getColor();
            String size = productList.get(i).getSize();
            int price = productList.get(i).getPrice();
            amount += price;
            System.out.println((i+1)+") Type:  "+type + " Color  " + color + " Size:  " + size + " Price  " + price);
        }
        System.out.println("_________________________________________________________________________________________________________________");
        return amount;
    }



    public static void main(String[] args) {


        // We will need this to get customer info and choice
        Scanner scan = new Scanner(System.in);

        // WELCOME & SIGN IN or LOGIN :  Welcome and registering user
        welcomeToShopMessage();
        Customer customer = identifyUser();
        // one for user in data base with login  two (2) for guest shopper
        int userType = customer.getFirstName().equals("guest") ? 1 : 2 ;
        // welcome the user with his information ( name and lastname )
        welcomeCustomer(customer, userType);

        boolean boughtSomething = false;
        boolean exitShop = false;

        // DISPLAYING ITEM AND SHOPPING
        List<Product> shoppingCart = new ArrayList<Product>();
        int amountToPay = 0;
        List<Product> productList = ProductRepository.findAll();
        int availableChoice = productList.size();
        boolean outOfStock = availableChoice==0;
        int nbOfItemBought = 0;

        if (outOfStock){
            System.out.println("Sorry we are out of stock please come next time . Thank you");
        }else{
            displayAvailableItem(productList);

            while ( !exitShop){
                int chosenItem = scan.nextInt();
                exitShop = chosenItem ==0;

                if (exitShop ){
                    break;

                }else{
                    boolean wrongChoice = !((chosenItem >= 1) && (chosenItem <=availableChoice));

                    boolean chosenItemOutOfStock = !(wrongChoice) && (StorageRepository.getQtyByCode( productList.get( chosenItem -1).getProductCode() ) < 1);

                    //while( wrongChoice || chosenItemOutOfStock ){
                    while( wrongChoice ){
                        if (wrongChoice) { System.out.println("wrong product selected try again by pressing the number before the item of your choice");}
                        else if (chosenItemOutOfStock){ System.out.println("this product is out of stock choose an available product "); }
                        displayAvailableItem(productList);
                        chosenItem = scan.nextInt();
                        wrongChoice = !((chosenItem >= 1) && (chosenItem <=availableChoice));
                    }
                    System.out.println("you sucessfully bought the item \n select another product  or Press (0) to exit and Pay : ");
                    // we Add the product to the customer shopping cart and reduce the quantity from the storage
                    shoppingCart.add( productList.get(chosenItem -1));
                    StorageRepository.updateStorageByCode(productList.get( chosenItem -1).getProductCode(),1);
                    displayAvailableItem(productList);
                    boughtSomething = true;
                    nbOfItemBought ++;


                }
            }
        }


        // the customer Exist the shop we check if he bought something or not

        if (shoppingCart.size()==0){
            System.out.println("Visit us next time , you might find the perfect item for you");
        }else{ // shopping cart is not empty

            System.out.println("would you like to your order to be delivered to you for a minimum additional fee ? \n YES : Press (1) \n No : Press (2)" );

            int custChoice = scan.nextInt();

            while ( !(custChoice == 1 || custChoice ==2) ) {
                System.out.println(" Wrong choice please select again : \n - If you wish delivery press (1)  \n - If you do not wish delivery press (2) ");
                custChoice = scan.nextInt();
            }


            // we get/create the order id for this cust purchase
            // we get last order that is in our data base and we +1 to get our new orderId for this customer purchase.
            int orderId = OrderRepository.getLastOrderId() +1;

            // if the customer chose delivery we display delivery cpy and price
            System.out.println(" LeoMi Shop Bill : ");
            if ( custChoice == 1){
                List<Delivery> deliveryList = DeliveryRepository.findAll();
                int nbOfDeliveryCpy = deliveryList.size();
                displayDelivery(deliveryList);
                int chosenDelivery = scan.nextInt();
                while (  !( chosenDelivery >=1 && chosenDelivery <=nbOfDeliveryCpy )){
                    displayDelivery(deliveryList);
                    chosenDelivery = scan.nextInt();
                }
                Delivery cstDeliveryMethod = deliveryList.get(chosenDelivery-1);
                int deliveryId = cstDeliveryMethod.getDeliveryId();

                //OrderRepository.saveNewOrder(new Order(orderId, customer.getCustomerId(), deliveryId, shoppingCart.get(0).getProductID()));
                // and for each item both we save a new order row in the the order table with same order id and same customerId
                for ( int i =0;  i < nbOfItemBought ; i++){
                    OrderRepository.saveNewOrder(new Order(orderId, customer.getCustomerId(), deliveryId, shoppingCart.get(i).getProductID()));
                }


                int deliveryCost = cstDeliveryMethod.getDeliveryCost();

                amountToPay = displayBill(shoppingCart) + deliveryCost;
                System.out.println( " **** delivery : " + cstDeliveryMethod.getName() + " Price:  "+deliveryCost);
                System.out.println("_________________________________________________________________________________________________________________");
            }else{

                //OrderRepository.saveNewOrder(new Order(orderId, customer.getCustomerId(), shoppingCart.get(0).getProductID()));
                // and for each item both we save a new order row in the the order table with same order id and same customerId
                for ( int i =0;  i < nbOfItemBought ; i++){
                    OrderRepository.saveNewOrder(new Order(orderId, customer.getCustomerId(), shoppingCart.get(i).getProductID()));
                }
                amountToPay = displayBill(shoppingCart);
            }

            System.out.println(" Total to pay : ______________________________________________________ "+amountToPay);



        }






        /*System.out.println(CustomerRepository.getLastCustomerId());

        System.out.println(CustomerRepository.getLastAddedCustomer().toString());

        List<Customer> customers = CustomerRepository.findAll();
        System.out.println(customers.get(1).getLastName());

        for ( Customer cust : customers){
            System.out.println(cust.toString());
        }*/




    }
}
