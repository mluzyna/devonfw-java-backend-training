package com.devonfw.app.java.order.orderservice.dataaccess.api;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.devonfw.app.java.order.general.dataaccess.api.ApplicationPersistenceEntity;
import com.devonfw.app.java.order.orderservice.common.api.OrderStatus;
import com.devonfw.app.java.order.orderservice.common.api.OrderSummary;

/**
 * @author MLUZYNA
 */
@Entity(name = "OrderSummary")
public class OrderSummaryEntity extends ApplicationPersistenceEntity implements OrderSummary {

  private LocalDate creationDate;

  private CustomerEntity owner;

  private Set<ItemEntity> orderPositions;

  private Double price;

  private OrderStatus status;

  private static final long serialVersionUID = 1L;

  /**
   * @return creationDate
   */
  @Override
  public LocalDate getCreationDate() {

    return this.creationDate;
  }

  /**
   * @param creationDate new value of {@link #getcreationDate}.
   */
  @Override
  public void setCreationDate(LocalDate creationDate) {

    this.creationDate = creationDate;
  }

  /**
   * @return owner
   */
  @ManyToOne
  @JoinColumn(name = "ownerId")
  public CustomerEntity getOwner() {

    return this.owner;
  }

  /**
   * @param owner new value of {@link #getowner}.
   */
  public void setOwner(CustomerEntity owner) {

    this.owner = owner;
  }

  /**
   * @return orderPositions
   */
  @ManyToMany
  @JoinTable(name = "OrderPosition", joinColumns = @JoinColumn(name = "orderId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "itemId", referencedColumnName = "id"))
  public Set<ItemEntity> getOrderPositions() {

    return this.orderPositions;
  }

  /**
   * @param orderPositions new value of {@link #getorderPositions}.
   */
  public void setOrderPositions(Set<ItemEntity> orderPositions) {

    this.orderPositions = orderPositions;
  }

  /**
   * @return price
   */
  @Override
  public Double getPrice() {

    return this.price;
  }

  /**
   * @param price new value of {@link #getprice}.
   */
  @Override
  public void setPrice(Double price) {

    this.price = price;
  }

  /**
   * @return status
   */
  @Override
  @Enumerated(EnumType.STRING)
  public OrderStatus getStatus() {

    return this.status;
  }

  /**
   * @param status new value of {@link #getstatus}.
   */
  @Override
  public void setStatus(OrderStatus status) {

    this.status = status;
  }

  @Override
  @Transient
  public Long getOwnerId() {

    if (this.owner == null) {
      return null;
    }
    return this.owner.getId();
  }

  @Override
  public void setOwnerId(Long ownerId) {

    if (ownerId == null) {
      this.owner = null;
    } else {
      CustomerEntity customerEntity = new CustomerEntity();
      customerEntity.setId(ownerId);
      this.owner = customerEntity;
    }
  }

}
