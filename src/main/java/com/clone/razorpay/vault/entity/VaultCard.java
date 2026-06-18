package com.clone.razorpay.vault.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vault_card")
public class VaultCard {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.UUID)
    private UUID id;

    @Column(length = 4, nullable = false)
    private String lastFour;

    @Column(length = 6, nullable = false)
    private String bin;

    @Column(nullable = false)
    private byte[] encryptedPan;

    @Column(nullable = false)
    private byte[] encryptedDek;

    @Column(length = 50, nullable = false)
    private String brand;

    @Column(length = 2, nullable = false)
    private String expiryMonth;

    @Column(length = 2, nullable = false)
    private String expiryYear;

    @Column(length = 100)
    private String cardHolderName;

    private LocalDateTime deletedAt;
}
