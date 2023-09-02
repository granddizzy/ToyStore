package org.example.toyShop;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Shop {
    private Queue<Toy> prizeLine;
    public final String name;
    private ArrayList<Showcase> showcases;
    private Warehouse warehouse;
    private int lastShowcaseId;

    public Shop(String name) {
        prizeLine = new LinkedList<>();
        this.name = name;
        showcases = new ArrayList<>();
        warehouse = new Warehouse();
        lastShowcaseId = 0;
    }

    /**
     * Загружает данные из базы
     */
    public void init() {
        try (BufferedReader br = new BufferedReader(new FileReader("shop.db"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");

                if (data[0].equals("warehouse")) {
                    warehouse.add(new Toy(data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), data[4]));
                } else if (data[0].equals("prizeline")) {
                    prizeLine.add(new Toy(data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), data[4]));
                } else if (data[0].equals("showcase")) {
                    Showcase sc = this.getShowcaseById(Integer.parseInt(data[1]));
                    if (sc == null) {
                        showcases.add(new Showcase(Integer.parseInt(data[1]), data[2], Integer.parseInt(data[3])));
                    }
                } else if (data[0].equals("showcaseToy")) {
                    Showcase sc = this.getShowcaseById(Integer.parseInt(data[5]));
                    sc.addToy(new Toy(data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), data[4]));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void save() {
        File file = new File("shop.db");
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            fr = new FileWriter(file, false);
            br = new BufferedWriter(fr);

            for (Toy toy : warehouse.getToys()) {
                StringBuilder sb = new StringBuilder();
                sb.append("warehouse").append(";").append(toy.getBaseString()).append("\n");
                br.write(sb.toString());
            }

            for (Toy toy : prizeLine) {
                StringBuilder sb = new StringBuilder();
                sb.append("prizeline").append(";").append(toy.getBaseString()).append("\n");
                br.write(sb.toString());
            }

            for (Showcase sc : showcases) {
                StringBuilder sb = new StringBuilder();
                sb.append("showcase").append(";").append(sc.getBaseString()).append("\n");
                br.write(sb.toString());
            }

            for (Showcase sc : showcases) {
                for (Toy toy : sc.getToys()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("showcaseToy").append(";").append(toy.getBaseString()).append(";").append(sc.getBaseString()).append("\n");
                    br.write(sb.toString());
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addShowcase(String type, int size) {
        lastShowcaseId++;
        showcases.add(new Showcase(lastShowcaseId, type, size));
    }

    public Showcase getShowcaseById(int id) {
        for (Showcase sc : showcases) {
            if (sc.getId() == id) return sc;
        }
        return null;
    }

    public boolean delShowcase(Showcase showcase) {
        if (showcase.checkEmptyShowcase()) {
            showcases.remove(showcase);
            return true;
        }

        return false;
    }

    public int getNumberOfShoucases() {
        return showcases.size();
    }

    public Showcase getShowcaseByIndex(int index) {
        return showcases.get(index);
    }

    public ArrayList<Showcase> getShowcases() {
        return showcases;
    }

    public boolean checkFullShowcases() {
        for (Showcase showcase : showcases) {
            if (showcase.getToyQuantity() != showcase.getSize()) return false;
        }
        return true;
    }

    public boolean checkEmptyShowcases() {
        for (Showcase showcase : showcases) {
            if (showcase.getToyQuantity() > 0) return false;
        }
        return true;
    }

    /**
     * Проводит розыгрыш игрушек и добавляет в очередь на выдачу
     */
    public void holdALottery() {
        // создаем колесо
        WheelFortune wheelOfFortune = new WheelFortune();

        // добавляем шары с id игрушек из каждой витрины и шансом выпадения
        for (Showcase showcase : showcases) {
            for (Toy toy : showcase.getToys()) {
                wheelOfFortune.addNubmer(toy.getId(), toy.getWeight());
            }
        }

        // крутим колесо пока есть шары
        while (wheelOfFortune.getNumberOfBalls() > 0) {
            int prizeToyId = wheelOfFortune.run();

            // сектор ПРИЗ на барабане ;)
            prizeLine.add(this.getToyById(prizeToyId));
        }
    }

    public Toy getAPrizeToy() {
        Toy toy = prizeLine.poll();
        for (Showcase showcase : showcases) {
            if (showcase.contains(toy)) showcase.delToy(toy);
        }
        return toy;
    }

    public Queue<Toy> getPrizeLine() {
        return prizeLine;
    }

    public int getPrizeLineSize() {
        return prizeLine.size();
    }

    public Toy getToyById(int id) {
        for (Showcase showcase : showcases) {
            for (Toy toy : showcase.getToys()) {
                if (toy.getId() == id) return toy;
            }
        }

        return null;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }
}
