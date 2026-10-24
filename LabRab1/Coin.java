package LabRab1;

/**
 * Узел кругового односвязного списка, описывающий одну монету.
 * head = true  — герб вверх
 * head = false — решка вверх
 */
public class Coin {

    boolean head;
    Coin next;

    public Coin(boolean head) {
        this.head = head;
        this.next = null;
    }
}
