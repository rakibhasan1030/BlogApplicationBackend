package org.rakibhasan.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    long fieldValue;

    public ResourceNotFoundException(long fieldValue) {
        super(String.format( "User not found with ID '%s'", fieldValue ));
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName) {
        super(String.format( "User not found with email '%s'", resourceName ));
        this.resourceName = resourceName;
    }

    public ResourceNotFoundException(String resourceName, long fieldValue) {
        super(String.format( "'%s' not found with ID '%s'", resourceName, fieldValue ));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }
}
