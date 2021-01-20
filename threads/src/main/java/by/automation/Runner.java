package by.automation;

import by.automation.models.Tunnel;

import java.util.*;
import java.util.concurrent.*;

/**
 * Разработать консольное многопоточное приложение.
 * Использовать возможности, предоставляемые пакетом java.util.concurrent.
 * Все сущности, желающие получить доступ к ресурсу, должны быть потоками.
 * <p>
 * Тоннель.
 * В горах существует два железнодорожных тоннеля, по которым поезда могут двигаться в обоих направлениях.
 * По обоим концам тоннеля собралось много поездов. Обеспечить безопасное прохождение тоннелей в обоих направлениях.
 * Поезд можно перенаправить из одного тоннеля в другой при превышении заданного времени ожидания на проезд.
 */

public class Runner {
    public static void main(String[] args) {

        BlockingQueue<Tunnel> tunnels = new ArrayBlockingQueue<>(2, true, List.of(new Tunnel(), new Tunnel()));
        new Thread(new TrainStarter(tunnels, 20)).start();

    }
}
