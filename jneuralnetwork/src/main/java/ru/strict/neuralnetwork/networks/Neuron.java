package ru.strict.neuralnetwork.networks;

import java.util.Objects;
import java.util.UUID;

/**
 * Нейрон
 */
public class Neuron implements Cloneable {

    /**
     * Идентификатор нейрона
     */
    private UUID entryId;
    /**
     * Значение храняящееся в нейроне
     */
    private float value;
    /**
     * Наименование нейрона
     */
    private String caption;
    /**
     * Тип нейронной сети
     */
    private NeuronType type;

    //<editor-fold defaultstate="collapsed" desc="constructors">
    public Neuron(float value) {
        entryId = UUID.randomUUID();
        this.value = value;
        this.type = NeuronType.UNKNOWN;
        caption = "";
    }

    public Neuron(float value, String caption) {
        entryId = UUID.randomUUID();
        this.value = value;
        this.caption = caption;
        this.type = NeuronType.UNKNOWN;
    }

    public Neuron(UUID entryId, float value, String caption) {
        this.entryId = entryId;
        this.value = value;
        this.caption = caption;
        this.type = NeuronType.UNKNOWN;
    }

    public Neuron(float value, NeuronType type) {
        entryId = UUID.randomUUID();
        this.value = value;
        this.type = type;
        caption = "";
    }

    public Neuron(float value, String caption, NeuronType type) {
        entryId = UUID.randomUUID();
        this.value = value;
        this.caption = caption;
        this.type = type;
    }

    public Neuron(UUID entryId, float value, String caption, NeuronType type) {
        this.entryId = entryId;
        this.value = value;
        this.caption = caption;
        this.type = type;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get/Set">
    public UUID getEntryId() {
        return entryId;
    }

    public void setEntryId(UUID entryId) {
        this.entryId = entryId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public NeuronType getType() {
        return type;
    }

    public void setType(NeuronType type) {
        this.type = type;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Base override">
    @Override
    public String toString() {
        return String.format("neuron [%s]: value - %s; type - %s; caption - %s",
                entryId.toString(),
                value,
                type,
                caption);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Neuron) {
            Neuron object = (Neuron) obj;
            return Objects.equals(entryId, object.getEntryId())
                    && Objects.equals(value, object.getValue())
                    && Objects.equals(caption, object.getCaption())
                    && Objects.equals(type, object.getType());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(entryId, value, caption, type);
    }

    @Override
    public Neuron clone() {
        return new Neuron(UUID.fromString(entryId.toString()), value, caption, NeuronType.values()[type.ordinal()]);
    }
    //</editor-fold>
}
