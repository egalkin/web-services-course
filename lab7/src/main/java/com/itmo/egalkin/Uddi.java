package com.itmo.egalkin;

/**
 * @author egalkin
 * @since 29.06.2021
 */
public class Uddi {

    private Publisher publisher;
    private ServiceBrowser browser;

    public Uddi() {
        this.publisher = new Publisher();
        this.browser = new ServiceBrowser();
    }

    public static void main(String... args) {
        Uddi uddi = new Uddi();

        if (args.length != 2) {
            System.out.println("Usage: <command> <name>");
            return;
        }
        if (args[0].equals("search")) {
            uddi.search(args[1]);
        }
        if (args[0].equals("publish")) {
            uddi.publish(args[1]);
        }
    }


    public void search(String name) {
        this.browser.search(name);
    }

    public void publish(String name) {
        this.publisher.publish(name);
    }

}
