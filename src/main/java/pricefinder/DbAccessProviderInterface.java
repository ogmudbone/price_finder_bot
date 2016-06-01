package pricefinder;

import pricefinder.identity.DomainIdentity;

/**
 * Uses to save price element identity on same
 * domain between calls
 */
public interface DbAccessProviderInterface {

    /**
     * Calls to save identity data
     *
     * @param identity domain info
     */
    void write(DomainIdentity identity);

    /**
     * calls to remove db record
     * @param domain domain of online shop
     */
    void remove(String domain);

    /**
     * Calls to read identity string
     *
     * @param domain domain of online shop
     * @return identity of price element
     */
    DomainIdentity readIdentity(String domain);

}
