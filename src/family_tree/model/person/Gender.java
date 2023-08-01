package family_tree.model.person;

import java.io.Serializable;

public enum Gender implements Serializable {
    Male, Female;

    @Override
    public String toString() {
        if (this == Female) {
            return "Женский";
        } else {
            return "Мужской";
        }
    }

}
