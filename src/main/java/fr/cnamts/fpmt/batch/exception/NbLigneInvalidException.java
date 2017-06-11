package fr.cnamts.fpmt.batch.exception;

public class NbLigneInvalidException extends CustomJobFailingException {

    private static final long serialVersionUID = 5303296649695468738L;

    private static final String MESSAGE = "--- Le nombre total de lignes relatives à l'entité 110 n'est pas"
            + " identique à celui indiqué dans l'enregistrement 990. ---";

    public NbLigneInvalidException() {
        super(MESSAGE);
    }

}
