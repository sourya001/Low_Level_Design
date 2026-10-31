class PaymentService{
    public void makePayment(String accountId,double amount){
        System.out.println("Payment of ₹" + amount + " successful for account " + accountId);
    }
}
class SeatReservationService{
    public void reserveSeat(String movieId,String seatNumber){
        System.out.println("Seat " + seatNumber + " reserved for movie " + movieId);
    }
}
class NotificationService {
    public void sendBookingConfirmation(String userEmail) {
        System.out.println("Booking confirmation sent to " + userEmail);
    }
}
class LoyaltyPointsService {
    public void addPoints(String accountId, int points) {
        System.out.println(points + " loyalty points added to account " + accountId);
    }
}
class TicketService {
    public void generateTicket(String movieId, String seatNumber) {
        System.out.println("Ticket generated for movie " + movieId + ", Seat: " + seatNumber);
    }
}
class MovieBookingFacade{
    private PaymentService paymentService;
    private SeatReservationService seatReservationService;  
    private NotificationService notificationService;
    private LoyaltyPointsService loyaltyPointsService;
    private TicketService ticketService;

    public MovieBookingFacade(){
        this.paymentService = new PaymentService();
        this.seatReservationService = new SeatReservationService();
        this.notificationService = new NotificationService();
        this.loyaltyPointsService = new LoyaltyPointsService();
        this.ticketService = new TicketService();
    }

    public void bookMovieTicket(String accountId, String movieId, String seatNumber, String userEmail, double amount){
        paymentService.makePayment(accountId, amount);
        seatReservationService.reserveSeat(movieId, seatNumber);
        ticketService.generateTicket(movieId, seatNumber);
        loyaltyPointsService.addPoints(accountId, 10); 
        notificationService.sendBookingConfirmation(userEmail);
   System.out.println("Movie ticket booking completed successfully!");
    }
}
class Fascade {
    public static void main(String[] args) {
        MovieBookingFacade movieBookingFacade = new MovieBookingFacade();
        movieBookingFacade.bookMovieTicket("ACC123", "MOV456", "A10", "user@example.com", 500.0);
    }
}
