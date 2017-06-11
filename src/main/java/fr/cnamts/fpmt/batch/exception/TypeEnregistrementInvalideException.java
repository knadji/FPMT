package fr.cnamts.fpmt.batch.exception;

public class TypeEnregistrementInvalideException extends CustomJobFailingException {

    private static final long serialVersionUID = -3352277516673424795L;

    private static final String MESSAGE = "--- Le fichier ne contient pas au moins un des types d'enregistrements"
            + " attendus. ---";

    public TypeEnregistrementInvalideException() {
        super(MESSAGE);
    }
}
