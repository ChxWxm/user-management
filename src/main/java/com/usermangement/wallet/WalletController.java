package com.usermangement.wallet;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("")
    public List<Wallet> getWallets() {
        return walletService.getWallets();
    }

    @PostMapping("")
    public Wallet createWallet(@RequestBody WalletRequest request) throws Exception {
        return walletService.createWallet(request);
    }

    @GetMapping("/{id}")
    public Wallet getWallet(@PathVariable Integer id) {
        return walletService.getWalletById(id);
    }

    @PutMapping("/{id}")
    public Wallet updateWallet(@PathVariable Integer id, @RequestBody UpdateWalletRequest request) {
        return walletService.updateWalletById(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteWallet(@PathVariable Integer id) {
        walletService.deleteById(id);
    }

    @PutMapping("/set-all-active")
    public void setWalletAllActive() {
        walletService.activeAllWallet();
    }

    @DeleteMapping("/delete-all-wallet")
    public void deleteAllWalletIdMoreThan2() {
        walletService.deleteAllWalletIdMoreThan2();
    }
}

record WalletRequest(
        @NotNull
        @Size(min = 3, max = 20)
        String name,
        @Email
        @Size(min = 3, max = 20)
        String email
) {}

record UpdateWalletRequest(
        @NotNull
        @Size(min = 3, max = 20)
        String name
) {}