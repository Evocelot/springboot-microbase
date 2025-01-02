package hu.evocelot.sample.model;

import java.math.BigInteger;
import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Base class for all entity with audit fields.
 * 
 * @author mark.danisovszky
 */
@MappedSuperclass
public class AbstractIdentifiedAuditEntity {

    public AbstractIdentifiedAuditEntity() {
        insDate = OffsetDateTime.now();
        insUser = "unknown"; // TODO: Get current user.
        modDate = OffsetDateTime.now();
        modUser = "unknown"; // TODO: Get current user.
        version = BigInteger.valueOf(0);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", length = 36, nullable = false, unique = true)
    private String id;

    @Column(name = "INS_DATE", nullable = false)
    private OffsetDateTime insDate;

    @Column(name = "INS_USER", nullable = true)
    private String insUser;

    @Column(name = "MOD_DATE", nullable = false)
    private OffsetDateTime modDate;

    @Column(name = "MOD_USER", nullable = true)
    private String modUser;

    @Column(name = "VERSION", nullable = false)
    private BigInteger version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OffsetDateTime getInsDate() {
        return insDate;
    }

    public void setInsDate(OffsetDateTime insDate) {
        this.insDate = insDate;
    }

    public String getInsUser() {
        return insUser;
    }

    public void setInsUser(String insUser) {
        this.insUser = insUser;
    }

    public OffsetDateTime getModDate() {
        return modDate;
    }

    public void setModDate(OffsetDateTime modDate) {
        this.modDate = modDate;
    }

    public String getModUser() {
        return modUser;
    }

    public void setModUser(String modUser) {
        this.modUser = modUser;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }
}
