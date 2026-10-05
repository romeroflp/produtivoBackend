package com.example.produtivoBackend.domain.users.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestResetPasswordConfirm(@Email String resetToken,
                                          @NotBlank
                                          @Size(max = 12, min = 8)
                                          String newPassword,
                                          @NotBlank
                                          @Size(max = 12, min = 8)
                                          String confirmNewPassword) {
}
