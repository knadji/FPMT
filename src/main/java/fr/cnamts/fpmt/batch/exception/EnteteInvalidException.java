package fr.cnamts.fpmt.batch.exception;

import static java.lang.String.format;

public class EnteteInvalidException extends CustomJobFailingException {

    private static final long serialVersionUID = -8799980793017085391L;

    private static final String MESSAGE = "--- L'entête du fichier %s n'est pas conforme ---";

    public EnteteInvalidException(final String nomFichier) {
        super(format(MESSAGE, nomFichier));
    }

}