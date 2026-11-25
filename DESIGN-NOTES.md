# Frontend redesign brief

The attached frontend guide is a **process guide**, not a pixel-accurate visual reference: it asks for clear constraints, a master prompt, one-problem-at-a-time implementation, and focused iterations. Its screenshots illustrate those ideas and an example scroll effect; they do not prescribe a pet-shop layout.

## Master brief and constraints

- Project: The Paws Shop, a university Java OOP pet-shop management system with a separate React/Vite frontend and Spring Boot backend.
- Goal: a polished, calm Apple-inspired pet-care website that still makes the staff portal easy to find and use.
- Keep: the existing content model, navigation destinations, authenticated management, API routes, H2 data, sales, billing, roles, and separate folders. No changes to backend logic.
- Design: warm cream and soft green, system typography, ample space, rounded bento cards, large pet photography, a translucent sticky navigation bar, clear contrast, and no imitation Apple logos.
- Interaction: immediate press feedback, scrolling story with restrained section reveals, a pointer-responsive Three.js paw that settles naturally, no interaction lockout. Respect reduced motion, transparency and increased-contrast preferences.
- Layout: large hero followed by story, bento grid, care section, call to action and footer. Responsive on desktop and mobile. Preserve keyboard focus indicators.
- Deliverable: working source in separate folders, development instructions and a ZIP for the IDE.

## Focused changes made

1. Redesigned the public landing page without changing the API or management workflows.
2. Harmonised the existing login and dashboard surfaces with the new design without changing their operations.
3. Added two generated pet-care images and refined the existing Three.js paw to respond to the pointer rather than loop continuously.
4. Added reduced-motion, reduced-transparency and high-contrast fallbacks.
