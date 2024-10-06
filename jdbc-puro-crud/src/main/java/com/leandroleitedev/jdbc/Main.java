package com.leandroleitedev.jdbc;

import com.leandroleitedev.jdbc.entitys.Address;
import com.leandroleitedev.jdbc.entitys.Customer;
import com.leandroleitedev.jdbc.services.CustomerService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        CustomerService customerService = new CustomerService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("""
                    ==========================
                    |     MENU PRINCIPAL     |
                    ==========================
                    | 1. Buscar Cliente por ID|
                    | 2. Listar Todos Clientes|
                    | 3. Criar Novo Cliente   |
                    | 4. Atualizar Cliente    |
                    | 5. Deletar Cliente      |
                    | 6. Sair                 |
                    ==========================
                    Escolha uma opção:
                    """);

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (choice) {
                case 1 -> {
                    System.out.print("Digite o ID do cliente: ");
                    Long id = scanner.nextLong();
                    System.out.println(customerService.findCustomerForId(id));
                }
                case 2 -> customerService.allCustomers().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Digite o nome do cliente: ");
                    String name = scanner.nextLine();
                    System.out.print("Digite o email do cliente: ");
                    String email = scanner.nextLine();
                    Customer customer = new Customer();
                    customer.setName(name);
                    customer.setEmail(email);

                    List<Address> addresses = new ArrayList<>();
                    System.out.print("Quantos endereços deseja adicionar? ");
                    int addressCount = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha

                    for (int i = 0; i < addressCount; i++) {
                        System.out.print("Digite a rua: ");
                        String street = scanner.nextLine();
                        System.out.print("Digite o bairro: ");
                        String neighborhood = scanner.nextLine();
                        System.out.print("Digite o estado: ");
                        String state = scanner.nextLine();
                        Address address = new Address();
                        address.setStreet(street);
                        address.setNeighborhood(neighborhood);
                        address.setState(state);
                        addresses.add(address);
                    }

                    customerService.saveCustomer(customer, addresses);
                }
                case 4 -> {
                    System.out.println("Atualize");
                    System.out.print("Digite o nome do cliente: ");
                    String name = scanner.nextLine();
                    System.out.print("Digite o email do cliente: ");
                    String email = scanner.nextLine();
                    Customer customer = new Customer();
                    customer.setName(name);
                    customer.setEmail(email);

                    List<Address> addresses = new ArrayList<>();
                    System.out.print("Quantos endereços deseja adicionar? ");
                    int addressCount = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha

                    for (int i = 0; i < addressCount; i++) {
                        System.out.print("Digite a rua: ");
                        String street = scanner.nextLine();
                        System.out.print("Digite o bairro: ");
                        String neighborhood = scanner.nextLine();
                        System.out.print("Digite o estado: ");
                        String state = scanner.nextLine();
                        Address address = new Address();
                        address.setStreet(street);
                        address.setNeighborhood(neighborhood);
                        address.setState(state);
                        addresses.add(address);
                    }

                    customerService.saveCustomer(customer, addresses);
                }
                case 5 -> {
                    System.out.print("Digite o ID do cliente a ser deletado: ");
                    Long id = scanner.nextLong();
                    customerService.deleteCustomer(id);
                }
                case 6 -> {
                    running = false;
                    System.out.println("Encerrando o programa...");
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}
