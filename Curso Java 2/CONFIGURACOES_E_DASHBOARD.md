# 🎨 Sistema de Configurações e Dashboard

## 📋 Visão Geral

O sistema agora conta com um módulo completo de **personalização** e um **dashboard analítico** para gestão de funcionários.

---

## ⚙️ Sistema de Configurações

### Funcionalidades

#### 🎨 **Temas de Cores**
5 temas disponíveis com paletas de cores cuidadosamente selecionadas:

1. **Escuro** (Padrão)
   - Fundo: `#17202A`
   - Destaque: `#34D399` (Verde menta)
   - Ideal para: Trabalho noturno, redução de cansaço visual

2. **Claro**
   - Fundo: `#FFFFFF`
   - Destaque: `#10B981` (Verde esmeralda)
   - Ideal para: Ambientes bem iluminados

3. **Azul**
   - Fundo: `#0F172A`
   - Destaque: `#38BDF8` (Azul céu)
   - Ideal para: Profissionais corporativos

4. **Roxo**
   - Fundo: `#18181B`
   - Destaque: `#A855F7` (Roxo vibrante)
   - Ideal para: Criatividade e modernidade

5. **Verde**
   - Fundo: `#141D20`
   - Destaque: `#34D399` (Verde água)
   - Ideal para: Conforto visual prolongado

#### 🔤 **Tipografia**
- **Fontes disponíveis:**
  - Segoe UI (Padrão)
  - Arial
  - Verdana
  - Tahoma
  - Georgia
  - Times New Roman
  - Courier New
  - Consolas

- **Tamanho da fonte:** Ajustável de 10px a 20px
- **Tamanho padrão:** 13px

#### 👁️ **Preview em Tempo Real**
- Visualização instantânea das alterações
- Pré-visualização de títulos, textos normais e destaques
- Interface interativa e responsiva

### Como Usar

1. Acesse o Hub de Funcionários
2. Clique em **⚙️ Configurações** no menu lateral
3. Escolha um dos 5 temas de cores clicando no card
4. Selecione a fonte desejada no dropdown
5. Ajuste o tamanho da fonte usando o slider
6. Visualize as mudanças no painel de preview
7. Clique em **✅ Aplicar** para salvar

### Persistência de Dados

As configurações são salvas automaticamente em `configuracoes.dat` e carregadas na próxima inicialização do sistema.

---

## 📊 Dashboard Analítico

### Componentes do Dashboard

#### 1. **Painel de Métricas (Cards Superiores)**
Cinco cards com informações essenciais:

- **👥 Total:** Quantidade total de funcionários
- **✅ Ativos:** Funcionários com status ativo
- **💰 Média Salarial:** Salário médio de todos os funcionários
- **⚠️ Advertências:** Total de advertências registradas
- **⭐ Ocorrências Positivas:** Quantidade de reconhecimentos

#### 2. **Gráfico de Distribuição por Cargo**
- Visualização em barras horizontais
- Cores diferenciadas para cada cargo
- Escala automática baseada no cargo com mais funcionários
- Scroll para cargos adicionais

#### 3. **Lista de Cadastros Recentes**
- Últimos 5 funcionários cadastrados
- Informações: Nome, cargo e status
- Indicador visual de status (ativo/inativo)
- Avatar emoji para identificação rápida

#### 4. **Gráfico de Evolução Salarial**
- Gráfico de linhas mostrando tendências salariais
- Baseado nos últimos ajustes de todos os funcionários
- Valores formatados em moeda brasileira (R$)
- Eixos com escala automática
- Limite de 20 pontos para melhor visualização

### Características Visuais

- **Responsividade:** Adapta-se a diferentes resoluções
- **Cores Dinâmicas:** Segue o tema selecionado nas configurações
- **Fontes Personalizadas:** Utiliza as preferências do usuário
- **Renderização Anti-aliased:** Gráficos suaves e profissionais

### Como Acessar

1. No Hub de Funcionários
2. Clique em **📊 Dashboard** no menu lateral
3. Uma nova janela será aberta com a análise completa

---

## 🏗️ Estrutura de Arquivos

```
Interacao/
├── Configuracoes.java           # Gerenciamento de configurações
├── DialogConfiguracoes.java     # Interface de personalização
├── PainelDashboard.java         # Dashboard analítico completo
├── HubFuncionarios.java         # Hub principal (atualizado)
└── ...outros arquivos...
```

---

## 🔧 Detalhes Técnicos

### Configuracoes.java
- **Serializable:** Permite salvar/carregar preferências
- **Enum Tema:** 5 temas pré-configurados
- **Método aplicarTema():** Aplica cores do tema selecionado
- **Métodos helper:** getFonteTitulo(), getFonteNormal(), etc.

### DialogConfiguracoes.java
- **Interface modal:** Bloqueia interação com janela pai
- **Cards de tema:** Seleção visual com preview de cores
- **Slider interativo:** Ajuste de tamanho de fonte em tempo real
- **Atualização dinâmica:** Recria o HubFuncionarios com novas configurações

### PainelDashboard.java
- **Layout BorderLayout:** Organização eficiente dos componentes
- **GridLayout para métricas:** Cards alinhados horizontalmente
- **Classe interna GraficoLinhas:** Desenho customizado com Graphics2D
- **NumberFormat:** Formatação de moeda brasileira
- **Streams Java:** Cálculos eficientes de estatísticas

---

## 🎯 Próximas Melhorias Sugeridas

### Dashboard
- [ ] Gráfico de pizza para distribuição de status
- [ ] Timeline de eventos (admissões, demissões, promoções)
- [ ] Comparativo mensal de contratações
- [ ] Ranking de funcionários por desempenho
- [ ] Exportação de relatórios em PDF

### Configurações
- [ ] Tema personalizado (escolha livre de cores)
- [ ] Modo de alto contraste para acessibilidade
- [ ] Atalhos de teclado personalizáveis
- [ ] Backup automático de configurações
- [ ] Sincronização em nuvem

### Integração
- [ ] Notificações visuais de eventos importantes
- [ ] Widgets no hub principal mostrando métricas em tempo real
- [ ] Temas automáticos (claro de dia, escuro à noite)
- [ ] Animações de transição entre telas

---

## 📱 Interface do Sistema

### DialogConfiguracoes
```
┌────────────────────────────────────────┐
│ ⚙️ CONFIGURAÇÕES                       │
│ Personalize a aparência do sistema     │
├────────────────────────────────────────┤
│                                        │
│ 🎨 TEMA DE CORES                       │
│ ┌──────┐ ┌──────┐ ┌──────┐ ┌──────┐  │
│ │Escuro│ │Claro │ │ Azul │ │ Roxo │  │
│ └──────┘ └──────┘ └──────┘ └──────┘  │
│                                        │
│ 🔤 TIPOGRAFIA                          │
│ Fonte: [Segoe UI ▼]                   │
│ Tamanho: [────●────] 13px              │
│                                        │
│ 👁️ PREVIEW                             │
│ ┌────────────────────────────────────┐│
│ │ Título Grande                      ││
│ │ Texto normal do sistema...         ││
│ │ ✅ Texto em destaque               ││
│ └────────────────────────────────────┘│
│                                        │
│                  [❌ Cancelar] [✅ Aplicar]│
└────────────────────────────────────────┘
```

### PainelDashboard
```
┌──────────────────────────────────────────────────────┐
│ [👥 Total] [✅ Ativos] [💰 Média] [⚠️ Advs] [⭐ Pos] │
├──────────────────────────────────────────────────────┤
│                                                      │
│ 📊 Distribuição     │  🆕 Cadastros Recentes         │
│ por Cargo          │                                │
│                    │  👤 João Silva                 │
│ Gerente  ████████  │     Gerente                    │
│ Analista ████████  │                                │
│ Técnico  ████      │  👤 Maria Santos               │
│                    │     Analista                   │
├────────────────────┴────────────────────────────────┤
│                                                      │
│ 📈 Evolução Salarial Média                           │
│     ╱──╲                                            │
│    ╱    ╲──╲                                        │
│ ──╱          ╲──╲                                   │
│                                                      │
└──────────────────────────────────────────────────────┘
```

---

## 📖 Glossário

- **Card:** Painel informativo com métrica específica
- **Preview:** Visualização prévia das alterações
- **Tema:** Conjunto de cores coordenadas para interface
- **Dashboard:** Painel de controle com informações resumidas
- **Widget:** Componente visual interativo

---

## 🤝 Contribuições

Para adicionar novos temas ou melhorias:

1. Edite `Configuracoes.java` → adicione valor ao enum `Tema`
2. Implemente as cores no método `aplicarTema()`
3. Adicione o card visual em `DialogConfiguracoes.java`
4. Teste a persistência e compatibilidade

---

## 📄 Licença

Sistema desenvolvido para gestão interna de RH.
Código proprietário - Todos os direitos reservados © 2025

---

**Versão:** 2.0  
**Última atualização:** Janeiro 2025  
**Desenvolvido com:** Java Swing  
**Compatibilidade:** Java 19+
