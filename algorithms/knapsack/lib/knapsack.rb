class Knapsack
  INFINITY = '∞'.freeze

  def initialize(capacity, input)
    @capacity = capacity
    @items = input.map { |item| item.transform_keys(&:to_sym) }
    @table = { 0 => Array.new(@items.count) { 0 } }
  end

  def run
    1.upto(max_value).each do |alpha|
      add_alpha_values(alpha)
    end

    { table: @table }.merge(extract_solution)
  end

  private

  def max_value
    @_max_value ||= @items.map { |item| item[:value] }.sum
  end

  def add_alpha_values(alpha)
    initial =
      if item_at(1)[:value] == alpha
        [item_at(1)[:weight]]
      else
        [INFINITY]
      end

    @table[alpha] = initial

    2.upto(@items.count).each do |i|
      @table[alpha] << calc_alpha_item_value(alpha, i)
    end
  end

  def calc_alpha_item_value(alpha, idx)
    item = item_at(idx)
    prev_alpha = alpha - item[:value]
    table_at_prev_alpha = @table.fetch(prev_alpha, infinite_sequence)
    prev_i = idx - 2

    if table_at_prev_alpha[prev_i] == INFINITY
      @table[alpha][prev_i]
    elsif @table[alpha][prev_i] == INFINITY
      table_at_prev_alpha[prev_i] + item[:weight]
    else
      [table_at_prev_alpha[prev_i] + item[:weight], @table[alpha][prev_i]].min
    end
  end

  def item_at(idx)
    @items.find { |i| i[:index] == idx }
  end

  def infinite_sequence
    Array.new(@items.count) { INFINITY }
  end

  def extract_solution
    relevant_values = @table.select do |_, weights|
      weights.any? { |w| w != INFINITY }
    end

    value_weight_pairs = relevant_values.map { |val, weights| [val, weights.last] }
    best_pair = value_weight_pairs.select { |pair| pair.last <= @capacity }.last

    item_sequence = get_item_sequence(best_pair)

    { weight: best_pair.last, value: best_pair.first, items: item_sequence }
  end

  def get_item_sequence(pair)
    get_item_sequence_rec(pair, [])
  end

  def get_item_sequence_rec(pair, sequence)
    alpha = pair[0]
    value = pair[1]

    if alpha.zero?
      sequence
    else
      idx = @table[alpha].index(value)
      item_idx = idx + 1

      next_alpha = alpha - item_at(item_idx).fetch(:value)
      next_idx = idx - 1
      next_pair = [next_alpha, @table[next_alpha][next_idx]]

      get_item_sequence_rec(next_pair, sequence.concat([item_idx]))
    end
  end
end
