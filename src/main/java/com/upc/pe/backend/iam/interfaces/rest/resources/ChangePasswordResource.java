package com.upc.pe.backend.iam.interfaces.rest.resources;

public record ChangePasswordResource(String currentPassword, String newPassword) {}