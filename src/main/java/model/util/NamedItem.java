package model.util;

/**
 * This class models an object that has a name(String) property.
 * Useful to have a Choicebox hold an item while displaying its user-friendly name.
 * @param <T> - The type of the item contained
 */
public final class NamedItem<T> {
    private  final String name;
    private  final T item;

    /**
     * Constructor of this class.
     * @param name - name of the item
     * @param item - the item
     */
    public NamedItem(final String name, final T item) {
        this.name = name;
        this.item = item;
    }

    public String toString() {
        return name;
    }

    /**
     * getter for the field item.
     * @return item
     */
    public T getItem() {
        return item;
    }
}
