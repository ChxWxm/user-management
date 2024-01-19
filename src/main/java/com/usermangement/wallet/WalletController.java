package com.usermangement.wallet;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("")
    public List<Wallet> getWallets(@RequestParam("name") Optional<String> name) {
        return walletService.getWallets(name);
    }

    @PostMapping("")
    public Wallet createWallet(@Validated @RequestBody WalletRequest request) {
        return walletService.createWallet(request);
    }

    @GetMapping("/{id}")
    public Wallet getWallet(@PathVariable Integer id) {
        return walletService.getWalletById(id);
    }

    @PutMapping("/{id}")
    public Wallet updateWallet(@PathVariable Integer id, @Validated @RequestBody UpdateWalletRequest request) {
        return walletService.updateWallet(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteWallet(@PathVariable Integer id) {
        walletService.deleteWallet(id);
        return "Wallet " + id + " has deleted.";
    }
}

record WalletRequest(
        @NotNull
        @Size(min = 3, max = 30)
        String name,
        @Email
        String email
) {}

record UpdateWalletRequest(
        @NotNull
        @Size(min = 3, max = 30)
        String name
) {}