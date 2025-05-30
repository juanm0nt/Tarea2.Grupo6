package Stage4;

/**
 *La clase abstracta que representa un componente dentro de Publisher-Subscriber.
 *Todos los componentes tienes un nombre identificador.
 */
public abstract class Component {
    protected String name;

    /**
     * Constructor del componente.
     * @param name nombre del componente.
     */
    public Component(String name) {
        this.name = name;
    }

    /**
     * Obtiene el nombre del componente.
     * @return nombre del componente.
     */
    public String getName() {
        return name;
    }
}
