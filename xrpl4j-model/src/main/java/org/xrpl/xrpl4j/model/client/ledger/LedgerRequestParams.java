package org.xrpl.xrpl4j.model.client.ledger;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import org.xrpl.xrpl4j.model.client.XrplRequestParams;
import org.xrpl.xrpl4j.model.client.common.LedgerIndex;
import org.xrpl.xrpl4j.model.transactions.Hash256;

import java.util.Optional;

/**
 * Request parameters for the "ledger" rippled API method.
 */
@Value.Immutable
@JsonSerialize(as = ImmutableLedgerRequestParams.class)
@JsonDeserialize(as = ImmutableLedgerRequestParams.class)
public interface LedgerRequestParams extends XrplRequestParams {

  static ImmutableLedgerRequestParams.Builder builder() {
    return ImmutableLedgerRequestParams.builder();
  }

  /**
   * A 20-byte hex string for the ledger version to use.
   *
   * @return An optionally-present {@link Hash256} containing the ledger hash.
   */
  @JsonProperty("ledger_hash")
  Optional<Hash256> ledgerHash();

  /**
   * The ledger index of the ledger to use, or a shortcut string to choose a ledger automatically.
   *
   * @return A {@link LedgerIndex}. Defaults to {@link LedgerIndex#CURRENT}.
   */
  @JsonProperty("ledger_index")
  @Value.Default
  default LedgerIndex ledgerIndex() {
    return LedgerIndex.CURRENT;
  }

  /**
   * If true, return full information on the entire ledger. Ignored if you did not specify {@link #ledgerHash()}.
   * Defaults to false. (Equivalent to enabling transactions, accounts, and expand.)
   *
   * <p>Caution: This is a very large amount of data -- on the order of several hundred megabytes!
   *
   * <p>Note: You must be a rippled Admin to set to true.
   *
   * @return {@code true} if requesting full information on the entire ledger, otherwise {@code false}.
   *     Defaults to {@code false}.
   */
  @Value.Default
  default boolean full() {
    return false;
  }

  /**
   * If true, return information on accounts in the ledger. Ignored if you did not specify {@link #ledgerHash()}.
   * Defaults to false.
   *
   * <p>Caution: This returns a very large amount of data!
   *
   * <p>Note: You must be a rippled Admin to set to true.
   *
   * @return {@code true} if requesting account information, otherwise {@code false}.
   *     Defaults to {@code false}.
   */
  @Value.Default
  default boolean accounts() {
    return false;
  }

  /**
   * If true, return information on transactions in the specified ledger version. Defaults to false.
   * Ignored if you did not specify {@link #ledgerHash()}.
   *
   * @return {@code true} if requesting transactions, otherwise {@code false}.
   *     Defaults to {@code false}.
   */
  @Value.Default
  default boolean transactions() {
    return false;
  }

  /**
   * Provide full JSON-formatted information for transaction/account information instead of only hashes.
   * Defaults to false. Ignored unless you request {@link #transactions()}, {@link #accounts()}, or both.
   *
   * @return {@code true} if requesting expanded transactions, otherwise {@code false}. Always {@code true}.
   */
  @Value.Derived
  default boolean expand() {
    return true;
  }

  /**
   * If true, include the {@code "owner_funds"} field in the metadata of
   * {@link org.xrpl.xrpl4j.model.transactions.OfferCreate} transactions in the response. Defaults to false.
   * Ignored unless {@link #transactions()} and {@link #expand()} are true.
   *
   * @return {@code true} if requesting the {@code "owner_funds"} field, otherwise {@code false}.
   *     Defaults to {@code false}.
   */
  @JsonProperty("owner_funds")
  @Value.Default
  default boolean ownerFunds() {
    return false;
  }

  /**
   * If true, and {@link #transactions()} and {@link #expand()} are both also true, return transaction information
   * in binary format (hexadecimal string) instead of JSON format.
   *
   * @return {@code true} if requesting transactions in binary format, otherwise {@code false}. Always {@code false}.
   */
  @Value.Derived
  default boolean binary() {
    return false;
  }

  /**
   * If true, and the command is requesting the current ledger, includes an array of queued transactions in the results.
   *
   * @return {@code true} if requesting queued transactions, otherwise {@code false}.
   *     Defaults to {@code false}.
   */
  @Value.Default
  default boolean queue() {
    return false;
  }

}
