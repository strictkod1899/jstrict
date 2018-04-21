package ru.strict.neuralnetwork.data;

import java.util.UUID;

/**
 * Нейрон
 */
public class Neuron implements Cloneable{

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
        this.type = NeuronType.UNKNOW;
        caption = "";
    }

    public Neuron(float value, String caption) {
        entryId = UUID.randomUUID();
        this.value = value;
        this.caption = caption;
        this.type = NeuronType.UNKNOW;
    }

    public Neuron(UUID entryId, float value, String caption) {
        this.entryId = entryId;
        this.value = value;
        this.caption = caption;
        this.type = NeuronType.UNKNOW;
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
    public String toString(){
        return String.format("neuron [%s]: value - %s; type - %s; caption - %s", entryId.toString(), value, type, caption);
    }

    public boolean equals(Object obj){
        if(obj==null)
            return false;

        Neuron neuron = (Neuron) obj;
        return neuron.entryId.equals(entryId) && neuron.value == value && neuron.caption.equals(caption)
                && neuron.getType().equals(getType());
    }

    public Neuron clone(){
        return new Neuron(UUID.fromString(entryId.toString()), value, caption, NeuronType.values()[type.ordinal()]);
    }
    //</editor-fold>
}