package com.usermangement.wallet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Wallet w SET w.active=true")
    void setAllWalletActive();

    @Modifying
    @Transactional
    @Query("DELETE FROM Wallet w Where w.id > 2")
    void deleteAllWalletIdMoreThan2();
}
