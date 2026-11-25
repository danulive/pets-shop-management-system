# Incremental upgrade audit

## Preserved

- `frontend/src/main.jsx`, `frontend/src/style.css`, the existing landing hero, bento layout, Three.js paw, staff dashboard, authentication UI, pet/customer/product/sales/appointment flows, Vite setup, and image assets.
- Existing backend endpoints, Java packages, Maven/Spring Boot/H2 stack, `shop_users`, `customer`, `pet`, `appointment`, product and sales tables and existing rows. No database reset is required. H2 schema additions use Hibernate `ddl-auto=update`; back up the `backend/data` directory before upgrading.
- Existing admin and staff accounts and `BOOKED` appointment status remain supported. Existing appointments and pets without owner/doctor IDs remain legacy records; they are not automatically claimed by any customer account.

## Modified

- Backend: `model/{ShopUser,Pet,Dog,Cat,Appointment}.java`; `service/{SecurityConfig,SeedData}.java`; `web/ApiController.java`.
- Frontend: `src/main.jsx` (navigation, roles, links, appointments and admin integration); `README.md` (run guide, demo accounts and limitations).

## Added

- Backend domain: `User`, `Doctor`, `CustomerAccount`, `PetService`, `PetGrooming`, `VetConsultation`, `Message` and `DataMigration`.
- Repositories: `PetServiceRepository`, `MessageRepository`, `DataMigrationRepository`.
- Services/controllers: `AppointmentWorkflow`, `MessageService`, `ExtendedSeedData`, `ExtendedController`.
- Tests: `ExtendedDomainTest`, `ApiSecurityTest`.
- Frontend: `src/Extensions.jsx` and `src/extensions.css` for new pages, customer and doctor dashboards, and admin services/inquiries.

## Compatibility decisions

- The existing `Customer` is a standalone shop-record entity in its own table and is already used by `/api/customers`. Changing that same class into a `shop_users` subclass would move/break existing records and require login credentials for legacy records. Instead, `CustomerAccount extends ShopUser extends User` is the customer login subtype; the original `Customer` remains untouched. A new customer account is not automatically linked to a legacy customer record. This is a deliberate deviation from the literal class name to protect data and API compatibility.
- `User` is a JPA mapped superclass; `ShopUser` is the single-table JPA inheritance root, with `Admin`, `Staff`, `Doctor` and `CustomerAccount` as discriminator subtypes. The pre-existing password hash is not exposed in JSON.
- Added columns are nullable to preserve existing data. New owner-linked pets and appointments are scoped by account ID. The migration marker makes the extra sample-data seed run once; existing accounts/catalog items are not overwritten.
- The public catalog shows demo inventory and does not provide a customer checkout. Prices for newly seeded named products are illustrative, not real store prices. Admin/staff continue to record sales using the existing interface.
