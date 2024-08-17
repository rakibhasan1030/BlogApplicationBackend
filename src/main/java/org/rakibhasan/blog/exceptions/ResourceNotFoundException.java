package org.rakibhasan.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    long fieldValue;

    public ResourceNotFoundException(long fieldValue) {
        super(String.format( "User not found with ID '%l'", fieldValue ));
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName) {
        super(String.format( "User not found with email '%s'", resourceName ));
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException(String resourceName, long fieldValue) {
        super(String.format( "User '%s' not found with ID '%l'", resourceName, fieldValue ));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }
}
