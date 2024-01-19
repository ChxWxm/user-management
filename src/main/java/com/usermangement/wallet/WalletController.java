package com.usermangement.wallet;

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
    public Wallet createWallet(@Validated @RequestBody WalletRequest request) throws Exception {
        return walletService.createWallet(request);
    }

    @GetMapping("/{id}")
    public Wallet getWallet(@PathVariable Integer id) {
        return walletService.getWalletById(id);
    }
}

record WalletRequest(
        @NotNull
        @Size(min = 3, max = 30)
        String name
) {
}
